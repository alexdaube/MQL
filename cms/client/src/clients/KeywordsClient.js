import axios from 'axios'
import Promise from 'promise'

class KeywordsClient {
    constructor(uri) {
        this.uri = uri;
    }

    fetchKeywords() {
        return new Promise((resolve, reject) => {
            axios.get(this.uri).then(response => {
                resolve(response.data);
            }).catch(error => {
                reject(error);
            })
        })
    }
}

export default KeywordsClient;
