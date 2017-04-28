import td from 'testdouble';
import axios from 'axios';
import JunctionsClient from '../../src/clients/JunctionsClient';
import * as junctionAssembler from '../../src/clients/assemblers/junctionAssembler';

const URI = "a uri";

const JUNCTION = 'a junction';
const SYNONYM = 'a synonym';

let junctionsClient, fromDto, toDto;

beforeEach(() => {
    td.reset();
    junctionsClient = new JunctionsClient(URI);
    td.replace(junctionAssembler, 'fromDto');
    td.replace(junctionAssembler, 'toDto');
    td.replace(axios, 'get');
    td.replace(axios, 'post');
    td.replace(axios, 'delete');

    td.when(junctionAssembler.fromDto(JUNCTION)).thenReturn(JUNCTION);
    td.when(junctionAssembler.toDto(JUNCTION)).thenReturn(JUNCTION);
    td.when(axios.get(URI)).thenResolve({data: [JUNCTION]});
    td.when(axios.post(URI, JUNCTION)).thenResolve({});
    td.when(axios.delete(URI+"/"+JUNCTION)).thenResolve({});
    td.when(axios.post(URI+"/"+JUNCTION+"/keywords", {keyword: SYNONYM})).thenResolve({});
    td.when(axios.delete(URI+"/"+JUNCTION+"/keywords/"+SYNONYM)).thenResolve({});
});

test('When fetchJunctions, then return a fetchJunctionsPromise', (done) => {
    junctionsClient.fetchJunctions().then((data) => {
        expect(data).toContain(JUNCTION);
        done();
    });
});

test('When addJunction, then return an addJunctionPromise', (done) => {
    junctionsClient.addJunction(JUNCTION).then((data) => {
        expect(data).toContain(JUNCTION);
        done();
    });
});

test('When removeJunction, then return a removeJunctionPromise', (done) => {
    junctionsClient.removeJunction(JUNCTION).then((data) => {
        expect(data).toContain(JUNCTION);
        done();
    });
});

test('When addJunctionSynonym, then return an addJunctionSynonymPromise', (done) => {
    junctionsClient.addSynonym(JUNCTION, SYNONYM).then((data) => {
        expect(data).toContain(JUNCTION);
        done();
    });
});

test('When removeJunctionSynonym, then return a removeJunctionSynonymPromise', (done) => {
    junctionsClient.removeSynonym(JUNCTION, SYNONYM).then((data) => {
        expect(data).toContain(JUNCTION);
        done();
    });
});
