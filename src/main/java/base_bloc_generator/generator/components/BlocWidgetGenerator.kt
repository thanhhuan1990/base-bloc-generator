package base_bloc_generator.generator.components

import base_bloc_generator.generator.Generator

class BlocWidgetGenerator(
    blocName: String
) : Generator(blocName, templateName = "bloc_widget") {

    override fun fileName() = "${snakeCase()}_widget.${fileExtension()}"
}