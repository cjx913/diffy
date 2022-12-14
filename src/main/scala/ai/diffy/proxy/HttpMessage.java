package ai.diffy.proxy;

import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class HttpMessage {
    Map<String, String> headers;
    String body;

    public HttpMessage(){}
    public HttpMessage(HttpHeaders headers, String body) {
        this.headers = group(headers.entries());
        this.body = body;
    }

    public Map<String, String> getHeaders(){
        return this.headers;
    }
    private static Map<String, String> group(Iterable<Map.Entry<String, String>> entries){
        Map<String, List<String>> grouped = new TreeMap<>();
        entries.forEach(entry -> {
            grouped.putIfAbsent(entry.getKey(), new ArrayList<>());
            grouped.get(entry.getKey()).add(entry.getValue());
        });
        Map<String, String> values = new HashMap<>(grouped.size());
        grouped.entrySet().forEach(entry -> {
            values.put(entry.getKey(), String.join(",",entry.getValue().stream().sorted().toList()));
        });
        return values;
    }
    public String getBody(){
        return body;
    }

    public static HttpHeaders toHttpHeaders(Map<String, String> entries) {
        HttpHeaders result = EmptyHttpHeaders.INSTANCE.copy();
        entries.forEach((key, values) ->
            Arrays.stream(values.split(",")).forEach(value ->
                result.add(key, value)
            )
        );
        return result;
    }
    @Override
    public String toString() {
        String headers = this.getHeaders().entrySet().stream().map(entry -> entry.getKey() + " : " + entry.getValue()).reduce((e1, e2) -> e1 + "\n" + e2).orElse("");
        return "\n" + headers + "\n" + body;
    }
}
