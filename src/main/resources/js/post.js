
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

function loadComment(author, body, id, parentCommentId, created_utc, downs, ups, score, isSub){

  renderComment(author, body, id, parentCommentId, created_utc, downs, ups, score, isSub);

    if(isSub !== false || parentCommentId !== null) {
      console.log(parentCommentId);
      const pcId = document.getElementById("downs-" + parentCommentId); 

      const divElement = document.createElement("div");
      divElement.setAttribute("id", "subCommentDiv-" + id);
      pcId.after(divElement);
    } else {
      console.log(false);
    }

  /*try {
  console.log(body);
  const parent = document.getElementById(parentCommentId);
  parent.append(authorElement);
  }
  catch(err) {
    document.getElementById("downs").append(authorElement);
  }*/
}

function unixToReadable(timestamp) {
    let date = new Date(timestamp*1000);
    return ("Posted: " +
                date.getFullYear() +
          "/" + date.getMonth() +
          "/" + date.getDay() +
          " " + date.getHours() +
          ":" + date.getMinutes() +
          ":" + date.getSeconds());
}


function renderComment(author, body, id, parentCommentId, created_utc, downs, ups, score, isSub){

  const commentsElement = document.getElementById("comments");
  const bottomElement = document.getElementById("bottom");
  const authorElement = document.createElement("p");
  const bodyElement = document.createElement("p");
  const createdElement = document.createElement("p");
  const upsElement = document.createElement("p");
  const scoreElement = document.createElement("p");
  const downsElement = document.createElement("p");  

  authorElement.setAttribute("id", "author-" + id);
  authorElement.appendChild(document.createTextNode(author));
  commentsElement.after(authorElement);

  bodyElement.setAttribute("id", "body-" + id);
  bodyElement.appendChild(document.createTextNode(body));
  authorElement.after(bodyElement);

  createdElement.setAttribute("id", "created-" + id);
  createdElement.appendChild(document.createTextNode(unixToReadable(created_utc)));
  bodyElement.after(createdElement);

  upsElement.setAttribute("id", "ups-" + id);
  upsElement.appendChild(document.createTextNode(ups));
  createdElement.after(upsElement);
  
  scoreElement.setAttribute("id", "score-" + id);
  scoreElement.appendChild(document.createTextNode(score));
  upsElement.after(scoreElement);

  downsElement.setAttribute("id", "downs-" + id);
  downsElement.appendChild(document.createTextNode(downs));
  scoreElement.after(downsElement);

}

function onLoad(){

    const postId = getLastItem(window.location.href);


    let post = getJson("/json/reddit/" +  postId).then(function(data) {
        console.log(data);
        loadPost(data.author, data.title, data.body, data.createdTime, data.downs, data.ups, data.score, data.url);
    });

    let comments = getJson("/json/reddit/comments/" +  postId).then(function(commentsList) {
      //console.log(commentsList);
      for (const comment of commentsList) {
        console.log(comment);
        loadComment(comment.author, comment.body, comment.id, comment.parentCommentId, comment.createdTime, comment.downs, comment.ups, comment.score, comment.isSub);
    }
  });
}