const getQueryKeywordBadgeDetails = (queryKeywordType) => {
    switch (queryKeywordType.toUpperCase()) {
        case 'OPERATOR':
        case 'BETWEEN':
        case 'GREATER':
        case 'LESS':
        case 'EQUAL':
        case 'NOT':
        case 'LIKE':
        case 'OTHER':
            return {color: 'default', label: 'O'};
        case 'ATTRIBUTE':
            return {color: 'danger', label: 'A'};
        case 'ENTITY':
            return {color: 'primary', label: 'E'};
        case 'VALUE':
        case "DATE":
        case "DECIMAL":
        case "VARCHAR":
        case "INTEGER":
            return {color: 'success', label: 'V'};
        case 'JUNCTION':
        case "AND":
        case "OR":
            return {color: 'warning', label: 'J'};
        default:
            return {color: 'default', label: queryKeywordType.toUpperCase()};
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