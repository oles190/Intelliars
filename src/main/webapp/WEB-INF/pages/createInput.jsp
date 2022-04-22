<html>
<head>
    <title>First Jsp</title>
    <link rel="stylesheet" href="/styles/style.css" type="text/css"/>
</head>

<body>

<h1>Creating Category</h1>
<div class="main">
    <form action="/mvc/create" method="post">
        <div class="one">
            <p><input tabindex="1" type="text" placeholder="name" name="name"></p>
            <p><input tabindex="2" type="text" placeholder="price" name="price"></p>
            <p><input tabindex="3" type="text" placeholder="items" name="items"></p>
            <p><input type="submit" value="send"></p>

            <div>
                <input type="radio" name="gender" value="boy">one
            </div>
            <div>
                <input type="radio" name="gender" value="girl">two
            </div>
        </div>


    </form>
</div>
</body>
</html>