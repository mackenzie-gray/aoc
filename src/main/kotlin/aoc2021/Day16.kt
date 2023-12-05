package aoc2021

import lib.Puzzle
import java.math.BigInteger

val PACKET_VERSION = 0..2
val PACKET_TYPE_ID = 3..5

class Day16: Puzzle() {
    var versionSum: Long = 0;

    private fun parsePacketLiteral(data: String): Int {
        var b = ""
        var len = 0
        data.windowed(5, 5) {
            b += it.substring(1)
            len += 5
            if (it[0] == '0') {
                return@windowed;
            }
        }
        return len
    }

    private fun parseOperatorPacket(data: String): Int {
        var lengthTypeId = data[0];
        var len = 1

        if (lengthTypeId == '0') {
            var length = data.substring(1..15)
            var bitLength = Integer.parseInt(length, 2)
            len += 15
            while (len < bitLength + 16) {
                var pLen = parsePacket(data.substring(len))
                len += pLen
            }
        } else {
            var packetLength = Integer.parseInt(data.substring(1..12))
            len += 12
            for (i in 1..packetLength) {
                len += parsePacket(data.substring(len))
            }
        }
        return len
    }

    private fun parsePacket(packet: String): Int {
        if (packet.length < 16) {
            return packet.length;
        }

        var version = packet.substring(PACKET_VERSION);
        var type_id = packet.substring(PACKET_TYPE_ID);
        versionSum += Integer.parseInt(version, 2)
        var p_length = 6
        if (Integer.parseInt(type_id, 2) == 4) {
            p_length += parsePacketLiteral(packet.substring(6))
        } else {
            p_length += parseOperatorPacket(packet.substring(6))
        }

        return p_length
    }

    override fun partA(): Long {
        var b = BigInteger(input[0], 16).toString(2)
        for (i in 1..(b.length % 4)) {
            b = "0$b";
        }

        parsePacket(b)

        return versionSum
    }

    override fun partB(): Long {
        return 0L;
    }
}