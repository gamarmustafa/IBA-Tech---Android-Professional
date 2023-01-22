import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.math.absoluteValue

fun main() = runBlocking {
    val flow: Flow<Int> = flowOf(1, 2, 4, 7, 5)
    val differenceFlow = flow.difference()
    differenceFlow.collect{print("$it ") }
}

fun Flow<Int>.difference(): Flow<Int> {
    return this.zip(this.drop(1)) { prev, current -> current - prev } //1 2 3 -2
}
// here zip operator combines the original flow with the shifted version of itself
// in given example, (1,2,4,7,5) flow is combined with (2,4,7,5) flow as first element is dropped with drop(1)
// then it creates pairs of elements and calculates differences
