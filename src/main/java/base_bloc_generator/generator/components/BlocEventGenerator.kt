package base_bloc_generator.generator.components

import base_bloc_generator.generator.Generator

class BlocEventGenerator(
    blocName: String
) : Generator(blocName, templateName = "bloc_event") {

    override fun fileName() = "${snakeCase()}_event.${fileExtension()}"
}