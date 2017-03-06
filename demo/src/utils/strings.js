const undoCamelCasing = (camelCasedWord) => {
    return (camelCasedWord.replace(/([A-Z])/g, ' $1')
        .replace(/^./, word => {
            return word.toUpperCase();})
    );
};


export {undoCamelCasing};