import React from "react";
import {Badge} from "reactstrap";


export const documentBlocksMarkup = [
    {
        id: "what-is-mql",
        menuTitle: 'What is MQL?',
        title: <h3>What is MQL?</h3>,
        text: <p>
            MQL stands for <span className="font-italic">Multitel Query Language</span>.
            It is a user friendly query language that enables you to query the database
            from a user interface while using a common tongue.
            It enables you to make fined grain queries that shows you exactly what you want to look at.
            It just like writing SQL queries without knowing anything about the syntax!
        </p>,
    },

    {
        id: "how-it-works",
        menuTitle: 'How it works?',
        title: <h3>How it works?</h3>,
        text: <p>To make a query, you have to specify at least 4 components:</p>,
        orderedList: [
            <p>
                An <span className="font-italic font-weight-bold">entity</span>,
                which represents a database table or the precise thing for which we are making this query
            </p>,
            <p>
                An <span className="font-italic font-weight-bold">attribute</span>,
                which represents a specific field that describes an entity
            </p>,
            <p>
                An <span className="font-italic font-weight-bold">operator</span>,
                to say how we want to compare the attribute and the value that goes with it
            </p>,
            <p>
                A <span className="font-italic font-weight-bold">value</span>,
                to match the previously declared attribute
            </p>
        ]
    },
    {
        id: "entities",
        menuTitle: 'Entities',
        title: <h3>Entities</h3>,
        text: <p>
            They are basically the main data type you are looking to search into.
            Search queries start with it.
        </p>,
        examples: [
            <p><kbd>equipments</kbd> where id is 3</p>,
            <p><kbd>sites</kbd> where province = Quebec</p>
        ]
    },
    {
        id: "attributes",
        menuTitle: 'Attributes',
        title: <h3>Attributes</h3>,
        text: <p>
            They are fields owned by the <i>Entities</i> inside the database. The attributes can be prepended by the
            word <i>where</i>, simply follow an entity, or follow a junction keyword. Following the declaration of an attribute, there need to be an <i>operator</i> and
            a <i>value</i> for the attribute to be useful.
        </p>,
        examples: [
            <p>equipments where <kbd>id</kbd> is 3</p>,
            <p>sites <kbd>province</kbd> = Quebec</p>
        ]
    },
    {
        id: "simple-operators",
        menuTitle: 'Simple Operators',
        title: <h3>Simple Operators</h3>,
        text: <p>
            <Badge className="font-weight-bold">Equals</Badge> is called with <span className="font-weight-bold">is, =, in</span>
            <br/>
            <Badge className="font-weight-bold">Less than</Badge> is called with <span className="font-weight-bold">less than, {'<'}</span>
            <br/>
            <Badge className="font-weight-bold">Greater than</Badge> is called with <span className="font-weight-bold">greater than, {'>'}</span>
        </p>,
        examples: [
            <p>
                <Badge color="success" className="font-weight-bold">Equals</Badge> Entity attribute <kbd>is</kbd> 90 or
                <kbd>in</kbd> 100 or <kbd>=</kbd> 108
            </p>,
            <p>
                <Badge color="success" className="font-weight-bold">Less than</Badge> Entity attribute <kbd>{'<'}</kbd>
                100 or <kbd>less than</kbd> 200
            </p>,
            <p>
                <Badge color="success" className="font-weight-bold">Greater than</Badge> Entity attribute is <kbd>greater
                than</kbd> 300 and <kbd>{'>'}</kbd> 70
            </p>
        ]
    },
    {
        id: "complex-operators",
        menuTitle: 'Complex Operators',
        title: <h3>Complex Operators</h3>,
        text: <p>
            <Badge className="font-weight-bold">in</Badge> with an equal operator specify multiple values
            <br/>
            <Badge className="font-weight-bold">between</Badge> to specify a range. And or : can be used between the
            upper bound and lower bound of the range
            <br/>
            <Badge className="font-weight-bold">Greater or equal</Badge> is called with <span
            className="font-weight-bold">{'>='}</span> or a combinaison of a greater operator with an equal operator
            <br/>
            <Badge className="font-weight-bold">Less or equal</Badge> is called with <span
            className="font-weight-bold">{'<='}</span> or a combinaison of a less operator with an equal operator
        </p>,
        examples: [
            <p>
                <Badge color="success" className="font-weight-bold">in</Badge> Entity attribute <kbd>is in</kbd> 10, 99,
                11
            </p>,
            <p>
                <Badge color="success" className="font-weight-bold">between</Badge> Entity attribute is <kbd>between 10
                and 99</kbd> or <kbd>between 50:80</kbd>
            </p>,
            <p>
                <Badge color="success" className="font-weight-bold">Greater or equal</Badge> Entity attribute <kbd>is
                greater or equal to</kbd> 9
            </p>,
            <p>
                <Badge color="success" className="font-weight-bold">Less or equal</Badge> Entity attribute <kbd>is less
                or equal to</kbd> 10
            </p>
        ]
    },
    {
        id: "types",
        menuTitle: 'Types',
        title: <h3>Types</h3>,
        text: <p>
            MQL supports numbers, strings and dates. Dates will have to follow this simple pattern
            to be valid: yyyy-MM-dd
        </p>,
        examples: [
            <p>
                Entity dateAttribute is between <kbd>1997-10-30</kbd> and <kbd>2016-06-26</kbd>
            </p>,
        ]
    },

    {
        id: "keyword-fallback",
        menuTitle: 'Keyword Fallback',
        title: <h3>Keyword Fallback</h3>,
        text: <p>
            Sometimes it's nice to have multiple conditions or multiple values for the same attribute.
            In SQL, you would have to give it a junction keyword and specify the attribute again before giving a second
            value.
            In MQL, we can omit renaming the attribute after the junction keyword. MQL will always default back
            to the last specified keyword in the query. This principle can be applied for entity, attribute and
            operator keywords.
        </p>,
        examples: [
            <p>
                Entity attributeName is “first value” or attributeName is “second value”
                <Badge color="warning">SQL Way</Badge>
            </p>,
            <p>
                Entity attributeName is “first value” or “second value”
                <Badge color="success">MQL Way</Badge>
            </p>,
        ]
    }
];
