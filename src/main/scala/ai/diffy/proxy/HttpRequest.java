package ai.diffy.proxy;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.Map;

public class HttpRequest extends HttpMessage {
    private  String method;
    private  String uri;
    private  String path;
    private  Map<String, String> params;

    public HttpRequest(){
        super();
    }
    public HttpRequest(String method, String uri, String path, Map<String, String> params, HttpHeaders headers, String body) {
        super(headers, body);
        this.method = method;
        this.uri = uri;
        this.path = path;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "path = "+ path+"\n"+"params =\n"+ params+"\n"+"message =\n"+ super.toString()+"\n";
    }
}
