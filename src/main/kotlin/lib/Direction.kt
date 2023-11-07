package lib

enum class Direction(var vec: Vector2) {
    NORTH(
        Vector2(0,1)
    ),
    NORTHEAST(
        Vector2(1,1)
    ),
    EAST(
        Vector2(1,0)
    ),
    SOUTHEAST(
        Vector2(1,-1)
    ),
    SOUTH(
        Vector2(0,-1)
    ),
    SOUTHWEST(
        Vector2(-1,-1)
    ),
    WEST(
        Vector2(-1, 0)
    ),
    NORTHWEST(
        Vector2(-1, 1)
    )
}