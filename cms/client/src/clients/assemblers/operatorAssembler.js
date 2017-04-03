export function toDto(operator) {
    return {
        type: operator.name,
        keywords: operator.synonyms
    };
}

export function fromDto(operator) {
    return {
        name: operator.type,
        synonyms: operator.keywords,
    };
}