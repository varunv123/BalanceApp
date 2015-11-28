package com.jsphdev.ws.remote;

import com.jsphdev.entities.model.Profile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.jsphdev.adapter.WebService.IRemoteClient;


/**
 * Created by vikramn on 11/13/15.
 */

public class WebServiceClient implements IRemoteClient {


    @Override
    public ClientResponse getResponse(String URL, String input) throws Exception {
        Client client = Client.create();

        WebResource webResource = client.resource(URL);
        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        return response;
    }

    @Override
    public ClientResponse getResponse(String URL) throws Exception {
        Client client = Client.create();

        WebResource webResource = client.resource(URL);
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        return response;
    }

    public Profile getProfile(String URL) throws Exception {
        Client client = Client.create();
        WebResource webResource = client.resource(URL);
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        Profile profile = response.getEntity(Profile.class);
        System.out.println(profile);
        return profile;
    }

}