{
  description = "Dev shell";
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    gitignore = {
      url = "github:hercules-ci/gitignore.nix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
    mavenix = {
      url = "github:nix-community/mavenix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs = { self, nixpkgs, flake-utils, gitignore, mavenix }:
    let

      customOut = flake-utils.lib.eachDefaultSystem (system:
        let
          system = "x86_64-linux";
          pkgs = import nixpkgs {inherit system;};
          mv = pkgs.callPackage mavenix {};
          args = {inherit system; mavenix = mv; jdk = pkgs.jdk20; };
          buildMv = p: mv.buildMaven{ src=p; doCheck = false; infoFile = p+"/mavenix.lock"; maven = pkgs.maven.overrideAttrs (_: { jdk = pkgs.jdk20; }); };
        in with pkgs ; {
          packages = rec {
            P1 = buildMv ./P1;
            default = P1;
          };
          devShells = rec {
            java = pkgs.mkShell {
              nativeBuildInputs = [
              ];
              buildInputs = [
                jdk20
                maven
                mavenix.packages.${system}.mavenix-cli
              ];
            };
            default = java;
          };
          # apps = rec {
          #   dev = {
          #     type = "app";
          #     program = ./nix/scripts/dev.sh;
          #   };
          #   devProd = {
          #     type = "app";
          #     program = ./nix/scripts/devProd.sh;
          #   };
          #   start = {
          #     type = "app";
          #     program = ./nix/scripts/start.sh;
          #   };
          #   build = {
          #     type = "app";
          #     program = ./nix/scripts/build.sh;
          #   };
          #   default = dev;
          # };
        });
    in
    customOut;
}