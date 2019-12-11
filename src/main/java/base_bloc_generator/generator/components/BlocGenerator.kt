package base_bloc_generator.generator.components

import base_bloc_generator.generator.Generator

class BlocGenerator(
    blocName: String
) : Generator(blocName, templateName = "bloc") {

    override fun fileName() = "${snakeCase()}_bloc.${fileExtension()}"
}
