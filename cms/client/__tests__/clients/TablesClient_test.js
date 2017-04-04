import td from 'testdouble';
import axios from 'axios';
import TablesClient from '../../src/clients/TablesClient';
import * as tableAssembler from '../../src/clients/assemblers/tableAssembler';

const URI = "a uri";

const TABLE = 'a table';
const SYNONYM = 'a synonym';
const ATTRIBUTE = 'an attribute';
const FOREIGN_KEY = 'a foreign key';

let tablesClient, fromDto, toDto;

beforeEach(() => {
    td.reset();
    tablesClient = new TablesClient(URI);
    td.replace(tableAssembler, 'fromDto');
    td.replace(tableAssembler, 'toDto');
    td.replace(tableAssembler, 'fromAttributeDto');
    td.replace(tableAssembler, 'toAttributeDto');
    td.replace(tableAssembler, 'fromForeignKeyDto');
    td.replace(tableAssembler, 'toForeignKeyDto');
    td.replace(axios, 'get');
    td.replace(axios, 'post');
    td.replace(axios, 'delete');

    td.when(tableAssembler.fromDto(TABLE)).thenReturn(TABLE);
    td.when(tableAssembler.toDto(TABLE)).thenReturn(TABLE);
    td.when(tableAssembler.fromAttributeDto(ATTRIBUTE)).thenReturn(ATTRIBUTE);
    td.when(tableAssembler.toAttributeDto(ATTRIBUTE)).thenReturn(ATTRIBUTE);
    td.when(tableAssembler.fromForeignKeyDto(FOREIGN_KEY)).thenReturn(FOREIGN_KEY);
    td.when(tableAssembler.toForeignKeyDto(FOREIGN_KEY)).thenReturn(FOREIGN_KEY);
    td.when(axios.get(URI)).thenResolve({data: [TABLE]});
    td.when(axios.post(URI, TABLE)).thenResolve({});
    td.when(axios.delete(URI+"/"+TABLE)).thenResolve({});
    td.when(axios.post(URI+"/"+TABLE+"/keywords", {keyword: SYNONYM})).thenResolve({});
    td.when(axios.delete(URI+"/"+TABLE+"/keywords/"+SYNONYM)).thenResolve({});
    td.when(axios.post(URI+"/"+TABLE+"/columns", ATTRIBUTE)).thenResolve({});
    td.when(axios.delete(URI+"/"+TABLE+"/columns/"+ATTRIBUTE)).thenResolve({});
    td.when(axios.post(URI+"/"+TABLE+"/columns/"+ATTRIBUTE+"/keywords", {keyword: SYNONYM})).thenResolve({});
    td.when(axios.delete(URI+"/"+TABLE+"/columns/"+ATTRIBUTE+"/keywords/"+SYNONYM)).thenResolve({});
    td.when(axios.post(URI+"/"+TABLE+"/foreign_keys", FOREIGN_KEY)).thenResolve({});
    td.when(axios.post(URI+"/"+TABLE+"/foreign_keys/remove", FOREIGN_KEY)).thenResolve({});
});

test('When fetchTables, then return a fetchTablesPromise', (done) => {
    tablesClient.fetchTables().then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When addTable, then return an addTablePromise', (done) => {
    tablesClient.addTable(TABLE).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When removeTable, then return an removeTablePromise', (done) => {
    tablesClient.removeTable(TABLE).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When addTableSynonym, then return an addTableSynonymPromise', (done) => {
    tablesClient.addSynonym(TABLE, SYNONYM).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When removeTableSynonym, then return an removeTableSynonymPromise', (done) => {
    tablesClient.removeSynonym(TABLE, SYNONYM).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When addTableAttribute, then return an addTableAttributePromise', (done) => {
    tablesClient.addAttribute(TABLE, ATTRIBUTE).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When removeTableAttribute, then return an removeTableAttributePromise', (done) => {
    tablesClient.removeAttribute(TABLE, ATTRIBUTE).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When addTableForeignKey, then return an addTableForeignKeyPromise', (done) => {
    tablesClient.addForeignKey(TABLE, FOREIGN_KEY).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When removeTableForeignKey, then return an removeTableForeignKeyPromise', (done) => {
    tablesClient.removeForeignKey(TABLE, FOREIGN_KEY).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When addTableAttributeSynonym, then return an addTableAttributeSynonymPromise', (done) => {
    tablesClient.addAttributeSynonym(TABLE, ATTRIBUTE, SYNONYM).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});

test('When removeTableAttributeSynonym, then return an removeTableAttributeSynonymPromise', (done) => {
    tablesClient.removeAttributeSynonym(TABLE, ATTRIBUTE, SYNONYM).then((data) => {
        expect(data).toContain(TABLE);
        done();
    });
});