package lib

class Vector2(var x: Float, var y: Float) {

    enum class Axis {
        X, Y
    }

    constructor(x: Int, y: Int): this(x.toFloat(), y.toFloat())
    val neighbours get() = mapOf(
            Direction.NORTH to Vector2(x, y + 1),
            Direction.NORTHEAST to Vector2(x + 1, y + 1),
            Direction.EAST to Vector2(x + 1, y),
            Direction.SOUTHEAST to Vector2(x + 1, y - 1),
            Direction.SOUTH to Vector2(x, y - 1),
            Direction.SOUTHWEST to Vector2(x - 1, y - 1),
            Direction.WEST to Vector2(x - 1, y),
            Direction.NORTHWEST to Vector2(x - 1, y + 1),
    )

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun hashCode(): Int {
        return (x * y * 100).toInt();
    }

    override fun equals(other: Any?): Boolean {
        other as Vector2;
        return x == other.x && y == other.y;
    }

    operator fun plus(b: Vector2): Vector2 {
        return Vector2(x + b.x, y + b.y)
    }

    operator fun minus(b: Vector2): Vector2 {
        return Vector2(x - b.x, y - b.y)
    }

    operator fun times(b: Vector2): Vector2 {
        return Vector2(x * b.x, y * b.y)
    }

    operator fun times(b: Float): Vector2 {
        return Vector2(x * b, y * b)
    }

    operator fun get(axis: Axis): Float {
        return when (axis) {
            Axis.X -> x;
            Axis.Y -> y
        }
    }
}