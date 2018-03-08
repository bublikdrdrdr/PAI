package app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlDocument {

    private List<String> headers = new LinkedList<>();
    private List<String> elements = new LinkedList<>();

    public HtmlDocument() {}

    public HtmlDocument(String title, boolean addBootstrap) {
        headers.add(getDefaultHeader());
        addTitle(title);
        if (addBootstrap) addBootstrap();
    }

    public void addHeader(String header){
        headers.add(header);
    }

    public Collection<String> getHeaders(){
        return headers;
    }

    public void addElement(String element){
        elements.add(element);
    }

    public Collection<String> getElements(){
        return elements;
    }

    public void addTitle(String title){
        headers.add("<title>"+title+"</title>");
    }

    public void addBootstrap(){
        headers.add("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
    }

    public static String getDefaultHeader(){
        return "<meta charset=\"UTF-8\">";
    }

    public HtmlDocument build(String element){
        addElement(element);
        return this;
    }

    @Override
    public String toString(){
        List<String> values = new LinkedList<>();
        values.add("<!DOCTYPE html>");
        values.add("<html>");
        values.add("<head>");
        values.addAll(headers);
        values.add("</head>");
        values.add("<body>");
        values.addAll(elements);
        values.add("</body>");
        values.add("</html>");
        return values.stream().collect(Collectors.joining());
    }
}
