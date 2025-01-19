package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.JoinRequest
import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus

class JoinRequestRepository {

    private var joinRequests: MutableList<JoinRequest> = mutableListOf()

    fun saveJoinRequest(joinRequest: JoinRequest) {
        joinRequests.add(joinRequest)
    }

    fun getPendingJoinRequestsByRoomId(roomId: String): List<JoinRequest> {
        return joinRequests.filter { it.roomId == roomId }
    }

    fun getPlayersInQueueCount(roomId: String): Int {
        return joinRequests.count { it.roomId == roomId }
    }

    fun getJoinRequestByPlayerId(playerId: Int): JoinRequest {
        return joinRequests.first { it.playerId == playerId }
    }

    fun deleteJoinRequest(roomId: String, playerId: Int) {
        joinRequests = joinRequests
            .filter { it.roomId != roomId || it.playerId != playerId }
            .toMutableList()
    }

    fun existsPendingRequest(playerId: Int): Boolean {
        return joinRequests.any { it.playerId == playerId }
    }

    fun existsRejectedRequest(roomId: String, playerId: Int): Boolean {
        return joinRequests.any { it.roomId == roomId && it.playerId == playerId && it.status == JoinRequestStatus.REJECTED }
    }

    fun acceptJoinRequest(roomId: String, playerId: Int) {
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