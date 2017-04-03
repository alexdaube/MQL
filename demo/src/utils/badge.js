const getQueryKeywordBadgeDetails = (queryKeywordType) => {
    switch (queryKeywordType.toUpperCase()) {
        case 'OPERATOR':
        case 'BETWEEN':
        case 'GREATER':
        case 'LESS':
        case 'EQUAL':
        case 'OTHER':
            return {color: 'default', letter: 'O'};
        case 'ATTRIBUTE':
            return {color: 'danger', letter: 'A'};
        case 'ENTITY':
            return {color: 'primary', letter: 'E'};
        case 'VALUE':
        case "DATE":
        case "DECIMAL":
        case "VARCHAR":
        case "INTEGER":
            return {color: 'success', letter: 'V'};
        case 'JUNCTION':
        case "AND":
        case "OR":
            return {color: 'warning', letter: 'J'};
        default:
            return {color: 'default', letter: suggestion.type};
    }
};


const KEYWORDS = {
    operator: 'OPERATOR',
    attribute: 'ATTRIBUTE',
    entity: 'ENTITY',
    value: 'VALUE',
    junction: 'JUNCTION'
};

export {getQueryKeywordBadgeDetails, KEYWORDS};