package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.JoinRequest
import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus

class JoinRequestRepository {

    private var joinRequests: MutableList<JoinRequest> = mutableListOf()

    fun saveJoinRequest(joinRequest: JoinRequest): Long {
        joinRequests.add(joinRequest)
        return 1
    }

    fun getPendingJoinRequestsByRoomId(roomId: Long): List<JoinRequest> {
        return joinRequests.filter { it.roomId == roomId }
    }

    fun getPlayersInQueueCount(roomId: Long): Int {
        return joinRequests.count { it.roomId == roomId }
    }

    fun getJoinRequestByPlayerId(playerId: Long): JoinRequest {
        return joinRequests.first { it.playerId == playerId }
    }

    fun deleteJoinRequest(roomId: Long, playerId: Long) {
        joinRequests = joinRequests
            .filter { it.roomId != roomId || it.playerId != playerId }
            .toMutableList()
    }

    fun existsPendingRequest(playerId: Long): Boolean {
        return joinRequests.any { it.playerId == playerId }
    }

    fun existsRejectedRequest(roomId: Long, playerId: Long): Boolean {
        return joinRequests.any { it.roomId == roomId && it.playerId == playerId && it.status == JoinRequestStatus.REJECTED }
    }

    fun acceptJoinRequest(roomId: Long, playerId: Long) {
        joinRequests = joinRequests
            .map {
                if (it.roomId == roomId && it.playerId == playerId) {
                    it.copy(status = JoinRequestStatus.ACCEPTED)
                } else {
                    it
                }
            }
            .toMutableList()
    }
}