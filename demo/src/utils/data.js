const flattenData = (dataList) => {
    return dataList.map((rowList) => {
        return rowList.reduce((rowObj, row) => {
            rowObj[row.name] = row.value;
            return rowObj;
        }, {});
    });
};

export {flattenData};
