package lib

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Line(
    private var point1: Vector2,
    private var point2: Vector2
) {
    var isHorizontal = point1.x == point2.x
    var isVertical = point1.y == point2.y

    fun getPoints(): List<Vector2>  {
        val dx = abs(point1.x - point2.x)
        val dy = abs(point1.y - point2.y)

        val sx = if (point2.x > point1.x) 1 else -1
        val sy = if (point2.y > point1.y) 1 else -1

        val points: MutableList<Vector2> = mutableListOf()
        var error = dx - dy

        var x = point1.x
        var y = point1.y

        while (x != point2.x || y != point2.y) {
            points.add(Vector2(x, y))
            var e2 = 2 * error
            if (e2 > -dy) {
              error -= dy
              x += sx
            }
            if (e2 < dx) {
                error += dx
                y += sy
            }
        }

        points.add(Vector2(x, y))
        return points
    }
}