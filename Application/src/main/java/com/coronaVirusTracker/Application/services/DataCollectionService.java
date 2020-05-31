package com.coronaVirusTracker.Application.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronaVirusTracker.Application.Models.LocationDetails;

@Service
public class DataCollectionService {

	private static String DataLink="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationDetails> stats=new ArrayList<>();
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
	    List<LocationDetails> newStats=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(DataLink)).build();
		HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
		StringReader in=new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			LocationDetails details=new LocationDetails();
			details.setState(record.get("Province/State"));
			details.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			details.setNewCases(latestCases);
            newStats.add(details);
		}
		 this.stats=newStats;   
	}
	public List<LocationDetails> getDetails() {
		// TODO Auto-generated method stub
		return stats;
	}
}
