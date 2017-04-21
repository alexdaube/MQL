const undoCamelCasing = camelCasedWord => {
    return (camelCasedWord.replace(/([A-Z])/g, ' $1')
            .replace(/^./, word => {
                return word.toUpperCase().trim();
            })
    );
};

const lowerCaseInput = value => {
    return value.trim().toLowerCase();
};

const isInputEmpty = value => {
    return value.trim().length === 0;
};

export {undoCamelCasing, lowerCaseInput, isInputEmpty};