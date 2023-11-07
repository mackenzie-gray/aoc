package lib

class Vector3(var x: Float, var y: Float, var z: Float) {
    override fun toString(): String {
        return "$x, $y, $z"
    }

    override fun hashCode(): Int {
        return (x * y * 100 * z + 5).toInt();
    }

    override fun equals(other: Any?): Boolean {
        other as Vector3;
        return x == other.x && y == other.y && z == other.z;
    }

    operator fun plus(b: Vector3): Vector3 {
        return Vector3(x + b.x, y + b.y, z + b.z)
    }

    operator fun minus(b: Vector3): Vector3 {
        return Vector3(x - b.x, y - b.y, z - b.z)
    }

    operator fun times(b: Vector3): Vector3 {
        return Vector3(x * b.x, y * b.y, z * b.z)
    }

    operator fun times(b: Float): Vector3 {
        return Vector3(x * b, y * b, z * b)
    }
}