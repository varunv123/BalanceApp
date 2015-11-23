package com.jsphdev.adapter.WebService;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by vikramn on 11/14/15.
 */
public interface IRemoteClient {

    public ClientResponse getResponse(String URL, String input) throws Exception;
    public ClientResponse getResponse(String URL) throws Exception;
}
