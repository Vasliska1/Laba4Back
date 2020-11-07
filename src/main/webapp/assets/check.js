drawSavedPoints();

document.getElementById('graph').addEventListener('click', function (e) {
        var form = $("#getForm");
        r = form[0].elements['getForm:r_input'].value;

        var svg = document.getElementById('graph');
        var htmlCoordinatesPoint = svg.createSVGPoint();
        htmlCoordinatesPoint.x = e.clientX;
        htmlCoordinatesPoint.y = e.clientY;
        var svgPoint = htmlCoordinatesPoint.matrixTransform(svg.getScreenCTM().inverse());
        var calcX = (svgPoint.x - 150) * r / 100;
        var calcY = -(svgPoint.y - 150) * r / 100;

        form[0].elements['getForm:x'].value = calcX.toFixed(3);
        form[0].elements["getForm:y_hinput"].value = calcY.toFixed(3);
        form[0].elements["getForm:y_input"].value = calcY.toFixed(3);

        var getX = svgPoint.x;
        var getY = svgPoint.y;

        var point = document.getElementById('point');

        setAttributes(point, {"cx": String(getX), "cy": String(getY), "r": r});

        drawSavedPoints();
    }
);

function setAttributes(el, options) {
    Object.keys(options).forEach(function (attr) {
        el.setAttribute(attr, options[attr]);

    })
}


function getPointsByRows() {
    var data = [];
    $('#tableForm table tbody tr').each(function (index) {
        data[index] = {};
        $(this).find("td").each(function (cellIndex) {
            data[index][$($("th")[cellIndex]).html()] = $(this).html().trim();
        });
    });
    return data;
}

function drawSavedPoints() {
    var points = getPointsByRows();
    if (points == null)
        return;


    for (var i in points) {
        boardElement = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        boardElement.setAttribute('cx', '100');
        boardElement.setAttribute('cy', '100');
        boardElement.setAttribute('r', '0');
        boardElement.setAttribute('stoke', "navy");
        boardElement.setAttribute('stroke-width', "5");
        document.getElementById("graph").appendChild(boardElement);

        var color = (points[i].Result == "true") ? "green" : "red";
        setAttributes(boardElement, {
            "cx": (points[i].X * 100 / points[i].R + 150),
            "cy": (150 - points[i].Y * 100 / points[i].R),
            "r": points[i].R,
            "stroke": color
        });
    }
}




