package utils;

import org.bson.Document;

import java.util.Map;

public interface S4Serializable {
    Document toDocument();

    void fromDocument(Document document);

    String getId();

    Map<String, String> getFields();
}
