package com.att.api.speech.service;

import java.io.UnsupportedEncodingException;

import com.att.api.oauth.OAuthToken;
import com.att.api.rest.APIResponse;
import com.att.api.rest.RESTClient;
import com.att.api.rest.RESTException;
import com.att.api.service.APIService;
import com.att.api.speech.model.SpeechResponse;

/**
 * Class that handles communication with the speech server.
 *
 */
public class TtsService extends APIService {

    /**
     * @param fqdn the fully qualified domain name
     * @param token the oauth token to make requests with
     */
    public TtsService(String fqdn, OAuthToken token) {
        super(fqdn, token);
    }

    /**
     * If the server returned a successful response, this method parses the
     * response and returns a {@link SpeechResponse} object.
     *
     * @param response the response returned by the server
     * @return the server response as a binary byte[] array
     * @throws RESTException request error 
     */
    private byte[] parseSuccess(APIResponse wavResponse) throws RESTException {
        //decodes binary properly with iso-8859-1 charset
        try {
            return wavResponse.getResponseBody().getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RESTException(e);
        }
    }

    /**
     * If the server responds with a failure, this method returns a
     * {@link SpeechResponse} object with the failure message.
     *
     * @param response response to parse
     * @return error in a SpeechResponse object
     * @throws RESTException if unable to parse the passed-in response
     */
    private void parseFailure(APIResponse response) throws RESTException {
        String result;
        if (response.getResponseBody() == null) {
            result = String.valueOf(response.getStatusCode());
        } else {
            result = response.getResponseBody();
        }
        throw new RESTException(result);
    }

    /**
     * Sends the request to the server.
     *
     * @param contentType content type of request
     * @param speechText body of speech request
     * @param xArg additional arguments
     *
     * @return a byte array containing a x-wav audio file
     * @throws RESTException request error
     * @see SpeechResponse
     */
    public byte[] sendRequest(String contentType, String speechText, String xArg)
            throws RESTException  {

        final String endpoint = getFQDN() + "/speech/v3/textToSpeech";
        RESTClient restClient = new RESTClient(endpoint)
            .addAuthorizationHeader(getToken())
            .addHeader("Content-Type", contentType)
            .addHeader("Accept", "audio/x-wav");                         

        if (xArg != null && !xArg.equals("")) {
            restClient.addHeader("X-Arg", xArg);
        }

        APIResponse apiResponse = restClient.httpPost(speechText);

        byte[] wavBytes = null;
        int statusCode = apiResponse.getStatusCode();
        if (statusCode == 200 || statusCode == 201) {
            wavBytes = parseSuccess(apiResponse);
        } else if (statusCode == 204) {
            parseFailure(apiResponse);
        }

        return wavBytes;
    }
}
