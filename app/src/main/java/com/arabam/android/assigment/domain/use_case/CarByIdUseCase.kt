package com.arabam.android.assigment.domain.use_case

import com.arabam.android.assigment.common.Resource
import com.arabam.android.assigment.domain.model.ResultUI
import com.arabam.android.assigment.domain.repository.CarRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CarByIdUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend operator fun invoke(carId: Int?): Resource<ResultUI> {
        return try {
            Resource.Loading
            Resource.Success(repository.getCarById(carId))
        } catch (e: HttpException) {
            Resource.Error(e)
        }
        catch (e: IOException) {
            Resource.Error(e)
        }
    }
}
