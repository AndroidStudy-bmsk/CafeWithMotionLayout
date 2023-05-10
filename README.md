# CafeWithMotionLayout
모션레이아웃 사용해보기

### [Lorem Ipsum](https://www.lipsum.com/)
### [Lorem Picsum](https://picsum.photos/)
- https://picsum.photos/200/300
- assets/home.json
```json
{
  "user": {
    "nickname": "흰둥이",
    "starCount": 10,
    "totalCount": 20
  },
  "appbarImage": "https://picsum.photos/200/300"
}
```

```kotlin
// Util.kt
fun Context.readData(): Home? {
    return try {
        val inputStream = this.resources.assets.open("home.json")

        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        String(buffer)

        val gson = Gson()
        gson.fromJson(String(buffer), Home::class.java)
    } catch (e: IOException) {
        null
    }
}
```
### [Mocky](https://designer.mocky.io/)