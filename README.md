# Script Converter

The Script Converter is a command line tool that converts LoadRunner Scripts into a NeoLoad project.

Warning: As the Script Converter is currently a preview version, no official support is provided for the tool. We would really appreciate your feedback on the product. Feel free to contact us at [Neotys support](https://www.neotys.com/support/contact.html).

## Installation

### Requirement
Java 8 must be installed on the machine used to run the Script Converter. ([Download Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)).

### Procedure
1. Download the [latest release](https://github.com/Neotys-Labs/Script-Converter/releases/latest)
2. Unzip in the folder of your choice

## Usage

The Script Converter converts LoadRunner Scripts from a folder previously created and including all the scripts to be processed. After the conversion process, the Script Converter creates a NeoLoad Project containing the Virtual User Paths corresponding to each LoadRunner script and provides a detailed summary of the events occurred during the process. The actions, functions and parameters processed can have 3 different status:
* **Supported**: Compatible with NeoLoad
* **Supported with a warning**: Available in NeoLoad but some options or arguments are not supported.
* **Not supported**: Cannot be processed by the converter.

For more information, see the [Coverage](#coverage) section.

After the script conversion is done, it is recommended to verify the content of the [migration log](#logs) to manually process elements either partially supported, or not supported.  

### Executable
* **Windows**: `script-converter.bat`
* **Linux/OSX**: `script-converter.sh`

### Arguments

Run a command prompt and enter the following line:
`-source <LR Scripts folder> -target <NeoLoad output directory> -project <NeoLoad Project name>`

* **LR Scripts folder**: Source directory where all scripts in each sub-directory will be converted into the output Project.
* **NeoLoad output directory**: The folder of the created NeoLoad Project.
* **NeoLoad Project name**: The name to assign to the NeoLoad project.

### Examples
* **Windows**: `.\script-converter.bat -source "C:\LoadRunnerScripts\MyAppX" -target "C:\Users\Documents\NeoLoadProjects\MyAppX" -project "MyAppX"`
* **Linux/OSX**: `./script-converter.sh -source "/home/user/LoadRunnerScripts/MyAppX" -target "/home/user/NeoLoadProjects/MyAppX" -project "MyAppX"`

## Coverage

Warning: The script Converter only supports Web - HTTP/HTML protocols.

This section lists the LoadRunner functions and parameters covered by the Script Converter.

### Functions
Below is the list of the LoadRunner functions that can be converted into a NeoLoad project:

| LoadRunner function | Script converter version | 
| ------------------- | ------------------------ |
| lr_start_transaction | Version 1.0 | 
| lr_end_transaction | Version 1.0 |
| lr_think_time | Version 1.0 |
| web_reg_find | Version 1.0 | 
| web_reg_save_param | Version 1.0 | 
| web_reg_save_param_ex | Version 1.1.1 | 
| web_reg_save_param_regexp | Version 1.1.1 | 
| web_reg_save_param_xpath | Version 1.1.1 | 
| web_reg_save_param_json | Version 1.1.1 | 
| web_url | Version 1.0 | 
| web_submit_data | Version 1.0 | 
| web_custom_request | Version 1.0 | 

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

## Logs

During the LoadRunner Scripts conversion, the tool created two log files in the NeoLoad project folder: 
* **migration_logs/migration.log**: This file contains the migration results. It contains for example the information of all elements either partially supported or not supported.
* **migration_logs/debug.log**: This file contains the debug results. It contains for example the stacktrace of any error encountered.

## NeoLoad compatibility and ChangeLog 

| Script converter version | NeoLoad compatibility | ChangeLog |
| ------------------------ | --------------------- | --------- |
|Version 1.1.1|Version 6.6+|<ul><li>Support of method **web_reg_save_param_ex**.</li><li>Support of method **web_reg_save_param_regexp**.</li><li>Support of method **web_reg_save_param_xpath**.</li><li>Support of method **web_reg_save_param_json**.</li></ul>|
|Version 1.1.0|Version 6.6+|Support **All** Search attribute (Headers or Body) for method **web_reg_save_param**.|
|Version 1.0|Version 6.4+|Initial version.|

## Feedbacks and bugs
Open [an issue](https://github.com/Neotys-Labs/Script-Converter/issues) or contact [Neotys support](https://www.neotys.com/support/contact.html), and provide [log files](#logs) and/or LoadRunner Scripts.

## How to contribute
The source code is not available yet. It will be opened soon to contribution.

