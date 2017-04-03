export function toDto(junction) {
    return {
        type: junction.name,
        keywords: junction.synonyms
    };
}

export function fromDto(junction) {
    return {
        name: junction.type,
        synonyms: junction.keywords
    };
}