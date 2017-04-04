function startRead() {
  // obtain input element through DOM

  var file = document.getElementById('file').files[0];
  if(file){
    getAsText(file);
  }
}

function getAsText(readFile) {

  var reader = new FileReader();

  // Read file into memory as UTF-16
  reader.readAsText(readFile, "UTF-8");

  // Handle progress, success, and errors
  reader.onprogress = updateProgress;
  reader.onload = loaded;
  reader.onerror = errorHandler;
}

function updateProgress(evt) {
  if (evt.lengthComputable) {
    // evt.loaded and evt.total are ProgressEvent properties
    var loaded = (evt.loaded / evt.total);
    if (loaded < 1) {
      // Increase the prog bar length
      // style.width = (loaded * 200) + "px";
    }
  }
}

function loaded(evt) {
  // Obtain the read file data
  var fileString = evt.target.result;
  var list = fileString.split('\n');
  
  
  var flare = {};
  flare.name = "Expand";
  var childs=[];
  for(var i=0;i<list.length;i++){
       
      var val = list[i].split("\t");
     // console.log(val);
      val[1] = val[1].substr(1,val[1].length-1);
      var children = [];
      var temp={};
      temp.name = val[0];

      var chil = val[1].split(",");
      for(var k=0;k<chil.length;k++){
        var t = {};
        t.name = chil[k];
        children.push(t);

      }
      temp.children = children;
      childs.push(temp);
  }
  flare.children = childs;

  console.log(JSON.stringify(flare));
}

function errorHandler(evt) {
  if(evt.target.error.name == "NotReadableError") {
    // The file could not be read
  }
}