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
    );

    fun turnRight(): Direction {
        return when(this) {
            NORTH -> NORTHEAST
            NORTHEAST -> EAST
            EAST -> SOUTHEAST
            SOUTHEAST -> SOUTH
            SOUTH -> SOUTHWEST
            SOUTHWEST -> WEST
            WEST -> NORTHWEST
            NORTHWEST -> NORTH
        }
    }

    fun turnRightSimple(): Direction {
        return when(this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            else -> throw Exception("Invalid direction")
        }
    }

    fun turnLeft(): Direction {
        return when(this) {
            NORTH -> NORTHWEST
            NORTHWEST -> WEST
            WEST -> SOUTHWEST
            SOUTHWEST -> SOUTH
            SOUTH -> SOUTHEAST
            SOUTHEAST -> EAST
            EAST -> NORTHEAST
            NORTHEAST -> NORTH
        }
    }

    fun turnLeftSimple(): Direction {
        return when(this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
            else -> throw Exception("Invalid direction")
        }
    }
}