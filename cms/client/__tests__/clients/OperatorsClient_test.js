import td from 'testdouble';
import axios from 'axios';
import OperatorsClient from '../../src/clients/OperatorsClient';
import * as operatorAssembler from '../../src/clients/assemblers/operatorAssembler';

const URI = "a uri";

const OPERATOR = 'a operator';
const SYNONYM = 'a synonym';

let operatorsClient, fromDto, toDto;

beforeEach(() => {
    td.reset();
    operatorsClient = new OperatorsClient(URI);
    td.replace(operatorAssembler, 'fromDto');
    td.replace(operatorAssembler, 'toDto');
    td.replace(axios, 'get');
    td.replace(axios, 'post');
    td.replace(axios, 'delete');

    td.when(operatorAssembler.fromDto(OPERATOR)).thenReturn(OPERATOR);
    td.when(operatorAssembler.toDto(OPERATOR)).thenReturn(OPERATOR);
    td.when(axios.get(URI)).thenResolve({data: [OPERATOR]});
    td.when(axios.post(URI, OPERATOR)).thenResolve({});
    td.when(axios.delete(URI+"/"+OPERATOR)).thenResolve({});
    td.when(axios.post(URI+"/"+OPERATOR+"/keywords", {keyword: SYNONYM})).thenResolve({});
    td.when(axios.delete(URI+"/"+OPERATOR+"/keywords/"+SYNONYM)).thenResolve({});
});

test('When fetchOperators, then return a fetchOperatorsPromise', (done) => {
    operatorsClient.fetchOperators().then((data) => {
        expect(data).toContain(OPERATOR);
        done();
    });
});

test('When addOperator, then return an addOperatorPromise', (done) => {
    operatorsClient.addOperator(OPERATOR).then((data) => {
        expect(data).toContain(OPERATOR);
        done();
    });
});

test('When removeOperator, then return a removeOperatorPromise', (done) => {
    operatorsClient.removeOperator(OPERATOR).then((data) => {
        expect(data).toContain(OPERATOR);
        done();
    });
});

test('When addOperatorSynonym, then return an addOperatorSynonymPromise', (done) => {
    operatorsClient.addSynonym(OPERATOR, SYNONYM).then((data) => {
        expect(data).toContain(OPERATOR);
        done();
    });
});

test('When removeOperatorSynonym, then return a removeOperatorSynonymPromise', (done) => {
    operatorsClient.removeSynonym(OPERATOR, SYNONYM).then((data) => {
        expect(data).toContain(OPERATOR);
        done();
    });
});