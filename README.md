## purchaseSaleCars
App diplomado




# Apis usada para listado de cliente
``` sh
Method:GET
Url:https://private-0110fd-iveroropeza.apiary-mock.com/car/car_list
```


## Install Git on Ubuntu 20.04
``` sh
sudo apt install git -y
```
## Run Homebrew installation script(don't run with root user)
``` sh
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
## Add Homebrew to your PATH
``` sh
eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"
```
## Check Brew is working fine
``` sh
brew install gcc
```
## Verfication Brew
``` sh
brew
```
# My Cloud Home FUSE file system

MCHFuse is a FUSE file system for mounting [Western Digital My Cloud Home](https://www.mycloud.com) devices.

It exposes the main storage area of your device using the
[WD My Cloud Home Off-Device API](https://developer.westerndigital.com/develop/wd-my-cloud-home/api.html).

## Prerequisites

To compile MCHFuse, you need at least go 1.13.

To run MCHFuse on OSX, you need `osxfuse` extension. You can install it with Homebrew
using the  command:

``` sh
brew cask install osxfuse
```

## Installing the latest release

To quickly install the latest pre-built binary of MCHFuse, you can execute the following command:

``` sh
curl -sSfL https://github.com/raulPlatimex/cloudHome/raw/main/install.sh | sudo sh -s -- -b /usr/local/bin
```
