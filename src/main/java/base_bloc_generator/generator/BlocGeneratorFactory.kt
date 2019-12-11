package base_bloc_generator.generator

import base_bloc_generator.generator.components.BlocEventGenerator
import base_bloc_generator.generator.components.BlocGenerator
import base_bloc_generator.generator.components.BlocStateGenerator
import base_bloc_generator.generator.components.BlocWidgetGenerator

object BlocGeneratorFactory {

    fun getBlocGenerators(blocName: String): List<Generator> {
        val bloc = BlocGenerator(blocName)
        val event = BlocEventGenerator(blocName)
        val state = BlocStateGenerator(blocName)
        val widget = BlocWidgetGenerator(blocName)
        return listOf(bloc, event, state, widget)
    }
}