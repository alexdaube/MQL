package infrastructure.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.keywords.*;
import infrastructure.CommunicationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

public class ConfigClient {

    private static final String ENTITY = "./src/main/configuration/entities_config.json";
    private static final String JUNCTION = "./src/main/configuration/junction_config.json";
    private static final String OPERATOR = "./src/main/configuration/operator_config.json";
    private final Gson gson;

    public ConfigClient() {
        this.gson = new GsonBuilder().create();
    }

    public Collection<EntityKeyword> findAllEntityKeyword() {
        try (final Reader data = new FileReader(ENTITY)) {
            return gson.fromJson(data, Entities.class).entities;
        } catch (FileNotFoundException e) {
            throw new CommunicationException("Could not retrieve junction configuration...");
        } catch (IOException e) {
            throw new CommunicationException("Unexpected error while fetching data...");
        }
    }

    public Collection<JunctionKeyword> findAllJunctionKeyword() {
        try (final Reader data = new FileReader(JUNCTION)) {
            return gson.fromJson(data, Junctions.class).junctions;
        } catch (FileNotFoundException e) {
            throw new CommunicationException("Could not retrieve junction configuration...");
        } catch (IOException e) {
            throw new CommunicationException("Unexpected error while fetching data...");
        }
    }

    public Collection<OperatorKeyword> findAllOperatorKeyword() {
        try (final Reader data = new FileReader(OPERATOR)) {
            return gson.fromJson(data, Operators.class).operators;
        } catch (FileNotFoundException e) {
            throw new CommunicationException("Could not retrieve junction configuration...");
        } catch (IOException e) {
            throw new CommunicationException("Unexpected error while fetching data...");
        }
    }
}
