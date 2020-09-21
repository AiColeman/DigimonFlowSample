package com.github.aicoleman.digimon_flow_sample.repository

import com.github.aicoleman.digimon_flow_sample.data.model.Digimon
import com.github.aicoleman.digimon_flow_sample.data.model.State
import com.github.aicoleman.digimon_flow_sample.data.remote.DigimonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class DigimonRepository @Inject constructor(
    private val digimonService: DigimonService
) {
    fun getDigimonList(): Flow<State<List<Digimon>>> {
        return flow {
            emit(State.loading())

            try {
                val Response = digimonService.fetchDigimonList()
                val Body = Response.body()

                if (Response.isSuccessful && Body != null) {
                    emit(
                        State.success(Body)
                    )
                } else {
                    emit(State.error<List<Digimon>>(Response.message()))
                }
            } catch (e: Exception) {
                emit(State.error("Network error"))
                e.printStackTrace()
            }

        }.flowOn(Dispatchers.IO)
    }

    fun getDigimonInfo(name: String): Flow<State<Digimon>> {
        return flow {
            emit(State.loading())

            try {
                val Response = digimonService.fetchDigimonInfo(name)
                val Body = Response.body()

                if (Response.isSuccessful && Body != null) {
                    emit(
                        State.success(Body)
                    )
                } else {
                    emit(State.error<Digimon>(Response.message()))
                }
            } catch (e: Exception) {
                emit(State.error("Network error"))
                e.printStackTrace()
            }

        }.flowOn(Dispatchers.IO)
    }

    fun getDigimonListByLevel(level: String): Flow<State<List<Digimon>>> {
        return flow {
            emit(State.loading())

            try {
                val Response = digimonService.fetchDigimonListByLevel(level)
                val Body = Response.body()

                if (Response.isSuccessful && Body != null) {
                    emit(
                        State.success(Body)
                    )
                } else {
                    emit(State.error<List<Digimon>>(Response.message()))
                }
            } catch (e: Exception) {
                emit(State.error("Network error"))
                e.printStackTrace()
            }

        }.flowOn(Dispatchers.IO)
    }

}