import chai, { expect } from 'chai';
import chaiEnzyme from 'chai-enzyme'
import sinon, { spy } from 'sinon';
import { mount, render, shallow } from 'enzyme';

chai.use(chaiEnzyme());

global.expect = expect;
global.sinon = sinon;
global.spy = spy;

global.mount = mount;
global.render = render;
global.shallow = shallow;