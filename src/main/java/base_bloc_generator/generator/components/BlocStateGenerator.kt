package base_bloc_generator.generator.components

import base_bloc_generator.generator.Generator

class BlocStateGenerator(
    blocName: String
) : Generator(blocName, templateName = "bloc_state") {

    override fun fileName() = "${snakeCase()}_state.${fileExtension()}"
}