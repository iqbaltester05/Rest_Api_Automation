package utilClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonFileReader {

    private static final String BASE_PATH = "src/test/resources/testdata/";
    private static final ObjectMapper mapper = new ObjectMapper();

    private static Map<String, Object> resolveFakerValues(Map<String, Object> data) {
        Map<String, Object> resolved = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String && ((String) value).startsWith("random")) {
                resolved.put(entry.getKey(), FakerUtil.randomString((String) value));
            } else {
                resolved.put(entry.getKey(), value);
            }
        }
        return resolved;
    }

    public static Map<String, Object> getObject(String fileName, String objectKey) {
        try {
            Map<String, Object> jsonFile = mapper.readValue(
                new File(BASE_PATH + fileName + ".json"),
                new TypeReference<Map<String, Object>>() {}
            );
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) jsonFile.get(objectKey);
            if (data == null) {
                throw new RuntimeException("Object key '" + objectKey + "' not found in " + fileName + ".json");
            }
            return resolveFakerValues(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fileName + ".json", e);
        }
    }

    public static <T> T getObject(String fileName, String objectKey, Class<T> pojoClass) {
        Map<String, Object> data = getObject(fileName, objectKey);
        return mapper.convertValue(data, pojoClass);
    }
}
