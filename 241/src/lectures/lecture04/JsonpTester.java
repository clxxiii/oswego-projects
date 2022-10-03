package lectures.lecture04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import javax.json.*;
import javax.json.stream.JsonGenerator;

public class JsonpTester {
	public static void main(String[] args) throws Exception {
		String filePath = FileSystems.getDefault().getPath("src", "lectures", "lecture04", "data", "roster.json")
				.toString();
		InputStream is = new FileInputStream(filePath);
		JsonReader jsonReader = Json.createReader(is);
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		is.close();

		System.out.println(jsonObject.getString("code"));
		JsonArray jsonArray = jsonObject.getJsonArray("students");

		String fileName = "roster-tmp.json";
		String fileOutputPath = FileSystems.getDefault().getPath("src", "lectures", "lecture04", "data", fileName)
				.toString();
		OutputStream os = new FileOutputStream(fileOutputPath);
		JsonWriter jsonWriter;

		JsonObjectBuilder joBuilder = Json.createObjectBuilder();
		JsonArrayBuilder jaBuilder = Json.createArrayBuilder();

		JsonObject newObject;
		for (JsonValue value : jsonArray) {
			JsonObjectBuilder stujoBuilder = Json.createObjectBuilder();
			System.out
					.println(value.asJsonObject().getString("name") + " " + value.asJsonObject().getString("id"));

			if (value.asJsonObject().getString("name").equals("John")) {
				stujoBuilder.add("name", "John 2");
				stujoBuilder.add("id", value.asJsonObject().getString("id"));
				jaBuilder.add(stujoBuilder);
			} else {
				jaBuilder.add(value);
			}
		}
		joBuilder.add("code", jsonObject.getString("code"));
		joBuilder.add("students", jaBuilder);
		newObject = joBuilder.build();

		Map<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory factory = Json.createWriterFactory(config);
		jsonWriter = factory.createWriter(os);

		jsonWriter.write(newObject);
		jsonWriter.close();
		os.close();
	}
}
