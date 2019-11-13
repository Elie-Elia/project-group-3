import axios from 'axios';
import config from '../../../config/index';

let frontendUrl = 'http://' + config.dev.host + ":" + config.dev.port;
let backendUrl = 'https://cors-anywhere.herokuapp.com/' + 'http://' +  config.dev.backendHost;

let AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    data() {
        return {
            lessons: null
        }
    },
    mounted: function() {
        AXIOS.get('/api/lesson/getall').then((response => {
            this.lessons = response.data; 
            console.log("made call");
            console.dir(this.lessons); 
        }))
    }, 
    methods: {
        deleteLesson: function(lessonId) {
            AXIOS.delete('/api/lesson/delete?lessonId='+lessonId).then((response => {
                console.log("i deleted the element!"); 
            }))
        }
    }
}