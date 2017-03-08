// import { expect } from "../test_helper";
// import {fetchQuery} from "../../src/actions/index";
// import {FETCH_QUERY} from "../../src/actions/types";
// import axios from 'axios'
// import sinon from 'sinon';
//
// const fakePayload = { foo: 'bar' };
// describe('actions', () => {
//     let sandbox;
//     beforeEach(() => sandbox = sinon.sandbox.create());
//     afterEach(() => sandbox.restore());
//
//     describe('fetchQuery', () => {
//
//         it('has correct type', () => {
//             sandbox.stub(axios, 'get').returns('');
//             const action = fetchQuery();
//             expect(action.type).to.equal(FETCH_QUERY);
//         });
//
//         // it('has correct payload', () => {
//         //     const resolved = new Promise((r) => r(fakePayload));
//         //     sandbox.stub(axios, 'get').returns(resolved);
//         //     const query = 'sites from ...';
//         //     const action = fetchQuery(query);
//         //     expect(action.payload).to.equal(fakePayload);
//         // });
//     });
// });
