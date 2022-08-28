
function getJson(url) {
    return new Promise(function(resolve, reject) {
      var xhr = new XMLHttpRequest();
      xhr.open('get', url, true);
      xhr.responseType = 'json';
      xhr.onload = function() {
        var status = xhr.status;
        if (status == 200) {
          resolve(xhr.response);
        } else {
          reject(status);
        }
      };
      xhr.send();
    });
  };

const getLastItem = thePath => thePath.substring(thePath.lastIndexOf('/') + 1);

function loadPost(author, title, body, createdTime, downs, ups, score, url){
    document.getElementById("author").innerHTML = "By: " + author;
    document.getElementById("title").innerHTML = title;
    document.getElementById("title2").innerHTML = title;
    document.getElementById("body").innerHTML = body;
    document.getElementById("createdTime").innerHTML = unixToReadable(createdTime);
    document.getElementById("downs").innerHTML = downs;
    document.getElementById("ups").innerHTML = ups;
    document.getElementById("score").innerHTML = score;
    document.getElementById("url").setAttribute("href", url);
    document.getElementById("url").innerHTML = url;
}

function unixToReadable(timestamp) {
    let date = new Date(timestamp);
    return ("Posted: " +
          date.getDate() +
          "/" + (date.getMonth()+1) +
          "/" + date.getFullYear() +
          " " + date.getHours() +
          ":" + date.getMinutes() +
          ":" + date.getSeconds());
}

function onLoad(){

    const postId = getLastItem(window.location.href);


    let post = getJson("/json/reddit/" +  postId).then(function(data) {
        console.log(data);
        loadPost(data.author, data.title, data.body, data.createdTime, data.downs, data.ups, data.score, data.url);
    });

    let comments = getJson("/json/reddit/comments/" +  postId).then(function(data) {

    });
}