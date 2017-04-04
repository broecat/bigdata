function task1(){
    var total=0;
    var margin = {top:20,right:20,bottom:20,left:20};
    var width = 500 - margin.left - margin.right,
        height = 500- margin.top - margin.bottom;

    var x = d3.scaleBand()
        .rangeRound([0, width])
        .padding(0.1);

    var y = d3.scaleLinear()
        .range([height, 30]);

    var chart = d3.select(".chart")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)

    d3.tsv("http://136.145.216.147:8080/task1.tsv", type, function(error, data) {
    //for(var i=0;i<data.length;i++){total+=data[i].value;}
    //for(var i=0;i<data.length;i++){data[i].value/=total;}
    
    x.domain(data.map(function(d) { return d.name; }));
    y.domain([0, d3.max(data, function(d) { return d.value; })]);
    
    var bar = chart.selectAll("g")
        .data(data)
        .enter().append("g")
        .attr("transform", function(d) { return "translate(" + x(d.name) + ",0)"; });

    bar.append("rect")
        .transition()
        .duration(1000)
        .attr("y", function(d) { return y(d.value); })
        .attr("height", function(d) { return height - y(d.value); })
        .attr("width", x.bandwidth());

    bar.append("text")
        .attr("class","value")
        .attr("x", x.bandwidth() / 2)
        .attr("y", function(d) { return y(d.value) + 3; })
        .attr("dy", "-.75em")
        .text(function(d) { return d.value; });

    bar.append("text")
        .attr("class","word")
        .attr("x", x.bandwidth() / 2)
        .attr("y", function(d) { return height + 3; })
        .attr("dy", ".75em")
        .text(function(d) { return d.name; });
    });
};

function type(d) {
  d.value = +d.value; // coerce to number
  return d;
}

function task3(){

var width = 174;
var height = 32;
var border = 10;
//console.log("Working...");
var container = d3.select("body"); 
//var color = d3.scaleCategory20();
d3.tsv("http://136.145.216.147:8080/task3.tsv", type, function(error, data) {

    // ****************  Individual colours  *********************
    data.forEach(function(d) { 
        //console.log(d.username);
        // create the SVG area for the rectangle
        svgArea = container.append("svg")
            .attr("width", width + (2*border))    
            .attr("height", height + (2*border));
            
        // Add in the rectangle
        svgArea.append("rect")
            .attr("transform", "translate("+border+","+border+")")
            .attr("height", height)
            .attr("width", width)
            .style("fill", function() {
                    return "hsl(" + Math.random() * 360 + ",100%,50%)";
                    })
            .attr("rx", 5)
            .attr("ry", 5); 

        // Add in the white background for the label
        svgArea.append("text")
            .attr("transform", 
                  "translate("+(border+(width/2))+","+(border+10)+")")
            .attr("dy", ".71em")
            .attr("text-anchor", "middle")
            .style("fill", "black")
            .style("font-weight", "bold")
            .attr("class", "shadow")
            .text(d.username); 

        // Add in the label
        svgArea.append("text")
            .attr("transform", 
                  "translate("+(border+(width/2))+","+(border+10)+")")
            .attr("dy", ".71em")
            .attr("text-anchor", "middle")
            .style("fill", "black")
            .style("font-weight", "bold")
            .text(d.username); 
    });
});

}

function task4(){
  var margin = {top: 30, right: 20, bottom: 30, left: 20},
    width = 960 - margin.left - margin.right,
    barHeight = 20,
    barWidth = width * .8;

var i = 0,
    duration = 400,
    root;

var tree = d3.tree()
    .nodeSize([0, 20]);

//var diagonal = d3.svg.diagonal()
   // .projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("http://136.145.216.147:8080/task4.json", function(error, flare) {
  if (error) throw error;

  flare.x0 = 0;
  flare.y0 = 0;
  update(root = flare);
});

function update(source) {

  // Compute the flattened node list. TODO use d3.layout.hierarchy.
  const treeRoot = d3.hierarchy(root);
  tree(treeRoot);

  var nodes = treeRoot.descendants();

  var height = Math.max(500, nodes.length * barHeight + margin.top + margin.bottom);

  d3.select("svg").transition()
      .duration(duration)
      .attr("height", height);

  d3.select(self.frameElement).transition()
      .duration(duration)
      .style("height", height + "px");

  // Compute the "layout".
  nodes.forEach(function(n, i) {
    n.x = i * barHeight;
  });

  // Update the nodes…
  var node = svg.selectAll("g.node")
      .data(nodes, function(d) { return d.id || (d.id = ++i); });

  var nodeEnter = node.enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
      .style("opacity", 1e-6);

  // Enter any new nodes at the parent's previous position.
  nodeEnter.append("rect")
      .attr("y", -barHeight / 2)
      .attr("height", barHeight)
      .attr("width", barWidth)
      .style("fill", color)
      .on("click", click);

  nodeEnter.append("text")
      .attr("dy", 3.5)
      .attr("dx", 5.5)
      .text(function(d) { return d.name; });

  // Transition nodes to their new position.
  nodeEnter.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
      .style("opacity", 1);

  node.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
      .style("opacity", 1)
    .select("rect")
      .style("fill", color);

  // Transition exiting nodes to the parent's new position.
  node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
      .style("opacity", 1e-6)
      .remove();

  // Update the links…
  var link = svg.selectAll("path.link")
      .data(treeRoot.links(), function(d) { return d.target.id; });

  // Enter any new links at the parent's previous position.
  link.enter().insert("path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
        var o = {x: source.x0, y: source.y0};
        return linker({source: o, target: o});
      })
    .transition()
      .duration(duration)
      .attr("d", linker);

  // Transition links to their new position.
  link.transition()
      .duration(duration)
      .attr("d", linker);

  // Transition exiting nodes to the parent's new position.
  link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return linker({source: o, target: o});
      })
      .remove();

  // Stash the old positions for transition.
  nodes.forEach(function(d) {
    d.x0 = d.x;
    d.y0 = d.y;
  });
}

// Toggle children on click.
function click(d) {
  if (d.children) {
    d._children = d.children;
    d.children = null;
  } else {
    d.children = d._children;
    d._children = null;
  }
  update(d);
}

function color(d) {
  return d._children ? "#3182bd" : d.children ? "#c6dbef" : "#fd8d3c";
}
function linker(d) {
  return "M" + d.source.y + "," + d.source.x
      + "C" + (d.source.y + d.target.y) / 2 + "," + d.source.x
      + " " + (d.source.y + d.target.y) / 2 + "," + d.target.x
      + " " + d.target.y + "," + d.target.x;
}

}
