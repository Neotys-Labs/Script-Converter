# Script Converter

The Script Converter makes it possible to convert LoadRunner Scripts into a NeoLoad project.

The Script Converter can be run as a command line tool.

Warning: The Script Converter is currently in beta version. No official support is provided for the tool. It also might involve in the future and we would really appreciate your feedback on the product. Feel free to contact us at [Neotys support](https://www.neotys.com/support/contact.html).

## Installation

### Requirement
Java 8 must be installed on the machine used to run the Script Converter. ([Download Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)).

### Procedure
1. Download the [latest release](https://github.com/Neotys-Labs/Script-Converter/releases/latest)
2. Unzip in the folder of your choice

## Usage

The Script Convert allows converting LoadRunner Scripts from a folder previously created and including all the scripts to be processed. After the conversion process, the Script Converter provides a detailed summary of the events occurred during the process. The actions, functions and parameters processed can have 3 different status:
* **supported**: compatible with NeoLoad
* **supported with a warning**: available in NeoLoad but might not respect the iso standard.
* **not supported**: cannot be processed by the converter.

For more information, see the [Coverage](#coverage) section.

### Executable
* **Windows**: script-converter.bat  
* **Linux/OSX**: script-converter.sh

### Arguments

Run a command prompt and enter the following line:
-source <LR Scripts folder> -target <NeoLoad output directory> -project <NeoLoad Project name>

* **LR Scripts folder**: The folder including the LoadRunner Scripts to convert.
* **NeoLoad output directory**: The folder to include all the Loadrunner scripts converted.
* **NeoLoad Project name**: The name to assign to the NeoLoad project.


### Examples
* **Windows**: .\script-converter.bat -source "C:\LoadRunnerScripts" -target "C:\Users\Documents\NeoLoadProjects\LRConversion" -project "LRConversion"
* **Linux/OSX**: ./script-converter.sh -source "/home/user/LoadRunnerScripts" -target "/home/user/NeoLoadProjects/LRConversion" -project "LRConversion"

## Coverage
<a name="Coverage"></a>

Warning: The script Converter only supports Web - HTTP/HTML protocols.

This section lists the LoadRunner functions and parameters covered by the Script Converter.

### Functions
Below is the list of the LoadRunner functions that can be converted into a NeoLoad project:
* lr_start_transaction / lr_end_transaction
* lr_think_time
* web_reg_find
* web_reg_save_param
* web_url
* web_submit_data
* web_custom_request

### Parameters
Below is the list of LoadRunner parameters that can be converted into a NeoLoad project:
* File Parameter
* Table Parameter
* Custom
* Unique Number

### Not Covered
* other functions
* logic (conditions and loops)
* custom C code
* actions other than in C language (.java, .vba...)

## Feedbacks and bugs
Open [an issue](https://github.com/Neotys-Labs/Script-Converter/issues) or contact [Neotys support](https://www.neotys.com/support/contact.html).

## How to contribute
The source code is not available yet. It will be opened soon to contribution.

