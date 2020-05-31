package utils;

import org.bson.Document;

public interface S4Serializable {
    Document toDocument();

    void fromDocument(Document document);
}
